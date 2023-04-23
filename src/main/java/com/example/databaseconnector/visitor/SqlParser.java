package com.example.databaseconnector.visitor;

import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;
import com.example.databaseconnector.context.DatabaseContextHolder;
import com.example.databaseconnector.enums.DatabaseType;
import com.example.databaseconnector.enums.SqlType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
public class SqlParser {
    public static String parser(String databaseType, String sql, SqlType sqlType) {
        switch (sqlType) {
            case SELECT:
                return parseSelect(databaseType, sql);

            case CREATE:
                String create = StringUtils.substringBefore(sql, SqlType.SELECT.getType());
                String select = String.format("%s %s", SqlType.SELECT.getType(), StringUtils.substringAfter(sql,
                        SqlType.SELECT.getType()));
                return String.format("%s %s", create, parser(databaseType, select, SqlType.SELECT));

            default:
                throw new RuntimeException("Unsupported sql.");
        }
    }

    private static String parseSelect(String databaseType, String sql) {
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLSelectStatement sqlStatement = (SQLSelectStatement) parser.parseStatement();

        // 1. 校验SQL，检验列，替换*，校验where表存不存在等等
        ValidatorVisitor validatorVisitor = new ValidatorVisitor();
        sqlStatement.accept(validatorVisitor);

        // 2. 如果为MetaOne类型，需要做前置处理
        DatabaseType dbType = DatabaseType.getDatabaseType(databaseType);
        if (dbType.equals(DatabaseType.MetaOne)) {
            getTableMap();
            // 首先在外层包一层
            // 克隆一个作为被包的子层
            SQLSelectStatement subQuery = sqlStatement.clone();
            // 设置父层的From
            SQLSelect select = sqlStatement.getSelect();
            SQLSelectQueryBlock query = (SQLSelectQueryBlock) select.getQuery();
            query.setFrom(subQuery.getSelect(), "alias");

            // 逻辑表替换为真实分表
            SplitTableReplaceVisitor splitTableReplaceVisitor = new SplitTableReplaceVisitor();
            sqlStatement.accept(splitTableReplaceVisitor);
        }

        // 3. 根据数据库类型，选择不同的适配器进行语法适配
        SQLASTVisitor visitor;

        switch (dbType) {
            case Mysql:
                visitor = new MysqlStatementVisitor();
                break;

            case Postgresql:
                visitor = new PostgresqlStatementVisitor();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + databaseType);
        }

        sqlStatement.accept(visitor);

        return sqlStatement.toString();
    }

    /**
     * 模拟调用MetaOne接口，获取真实物理表
     */
    private static void getTableMap() {
        //    根据表名，从MetaOne获取所有的分表和Schema的对应关系
        Map<String, List<String>> tableInfos = DatabaseContextHolder.getTableInfo();
        // 从MetaOne获取表对应的数据源类型和真实分表名称
        for (String tableName : tableInfos.keySet()) {
            if (tableName.equals("xdr.access_log")) {
                tableInfos.put(tableName,
                        Arrays.asList("test.access_log_18000", "test.access_log_18001", "test.access_log_18002"));
            }
        }
    }
}
