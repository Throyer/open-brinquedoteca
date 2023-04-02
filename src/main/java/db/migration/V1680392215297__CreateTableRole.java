package db.migration;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

/**
* @see "https://www.jooq.org/doc/3.1/manual/sql-building/ddl-statements"
*/
public class V1680392215297__CreateTableRole extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        var create = using(context.getConnection());
        create.transaction(configuration -> {
            using(configuration)
                .createTableIfNotExists("cargo")
                    .column("id", BIGINT.identity(true))
                    .column("nome", VARCHAR(255).nullable(false))
                .constraints(
                    constraint("cargo_pk").primaryKey("id"))
                .execute();
        });
    }
}