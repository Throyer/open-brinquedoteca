package db.migration;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

/**
* @see "https://www.jooq.org/doc/3.1/manual/sql-building/ddl-statements"
*/
public class V1680392537512__CreateTableCanto extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        var create = using(context.getConnection());
         create.transaction(configuration -> {
             using(configuration)
                 .createTableIfNotExists("canto")
                    .column("id", BIGINT.identity(true))
                    .column("nome", VARCHAR(255).nullable(true))
                    .column("descricao", CLOB(5000).nullable(true))
                    .column("created_at", TIMESTAMP.defaultValue(currentTimestamp()))
                    .column("updated_at", TIMESTAMP.nullable(true))
                    .column("deleted_at", TIMESTAMP.nullable(true))
                    .column("created_by", BIGINT.nullable(true))
                    .column("updated_by", BIGINT.nullable(true))
                    .column("deleted_by", BIGINT.nullable(true))
                 .constraints(
                     constraint("canto_pk").primaryKey("id"),
                     constraint("canto_created_by_fk").foreignKey("created_by").references("usuario", "id"),
                     constraint("canto_updated_by_fk").foreignKey("updated_by").references("usuario", "id"),
                     constraint("canto_deleted_by_fk").foreignKey("deleted_by").references("usuario", "id"))
                 .execute();
         });
    }
}