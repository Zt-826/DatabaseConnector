package com.example.databaseconnector.visitor;

import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;
import com.example.databaseconnector.context.DatabaseContextHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidatorVisitor implements SQLASTVisitor {
    public boolean visit(SQLSelectQueryBlock x) {
        return true;
    }

    public boolean visit(SQLPropertyExpr x) {
        // 设置表名
        String ownerName = x.getOwnerName();
        String name = x.getName();
        Map<String, List<String>> tableInfoMap = new HashMap<>();
        tableInfoMap.put(ownerName + "." + name, new ArrayList<>());
        DatabaseContextHolder.setTableInfo(tableInfoMap);
        return true;
    }
}
