package com.example.databaseconnector.visitor;

import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

public class PostgresqlStatementVisitor implements SQLASTVisitor {
    @Override
    public boolean visit(SQLPropertyExpr x) {
        String name = "\"" + x.getName() + "\"";
        x.setName(name);
        return true;
    }
}
