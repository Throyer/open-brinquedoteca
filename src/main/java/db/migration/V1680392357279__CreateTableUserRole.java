package db.migration;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

/**
* @see "https://www.jooq.org/doc/3.1/manual/sql-building/ddl-statements"
*/
public class V1680392357279__CreateTableUserRole extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        var create = using(context.getConnection());
        create.transaction(configuration -> {
            using(configuration)
                .createTableIfNotExists("usuario_cargo")
                    .column("usuario_id", BIGINT.nullable(true))
                    .column("cargo_id", BIGINT.nullable(true))
                .constraints(
                    constraint("user_role_fk").foreignKey("usuario_id").references("usuario", "id"),
                    constraint("role_user_fk").foreignKey("cargo_id").references("cargo", "id"))
                .execute();
        });
    }
}