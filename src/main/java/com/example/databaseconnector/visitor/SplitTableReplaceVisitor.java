package com.example.databaseconnector.visitor;

import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class SplitTableReplaceVisitor implements SQLASTVisitor {

    @Override
    public boolean visit(SQLPropertyExpr x) {
        return true;
    }

    @Override
    public boolean visit(SQLExprTableSource x) {
        return true;
    }

    @Override
    public boolean visit(SQLSelectQueryBlock x) {
        return true;
    }

    @Override
    public boolean visit(SQLSubqueryTableSource x) {
        SQLSelect select = x.getSelect();
        MySqlSelectQueryBlock query = (MySqlSelectQueryBlock) select.getQuery();
        if (query.getFrom() instanceof SQLExprTableSource) {

            List<String> tableNames = Arrays.asList("test.access_log_18000", "test.access_log_18001", "test" +
                    ".access_log_18002");

            SQLUnionQuery sqlUnionQuery = new SQLUnionQuery();
            sqlUnionQuery.setOperator(SQLUnionOperator.UNION_ALL);

            for (String tableName : tableNames) {
                MySqlSelectQueryBlock subQuery = query.clone();
                subQuery.setFrom(new SQLExprTableSource(tableName));
                sqlUnionQuery.addRelation(subQuery);
            }
            select.setQuery(sqlUnionQuery);
        }

        return true;
    }
}
