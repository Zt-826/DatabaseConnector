package com.example.databaseconnector.visitor;

import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

public class MysqlStatementVisitor implements SQLASTVisitor {
    @Override
    public boolean visit(SQLExprTableSource x) {
        return true;
    }
}
