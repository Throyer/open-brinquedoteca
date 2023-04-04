package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import static org.jooq.impl.DSL.using;

/**
 * @see "https://www.jooq.org/doc/3.1/manual/sql-building/ddl-statements"
 */
public class V1680634506516__DropColumnLastName extends BaseJavaMigration {
  @Override
  public void migrate(Context context) throws Exception {
    var create = using(context.getConnection());
    create.transaction(configuration ->
      using(configuration)
        .alterTable("usuario")
        .dropColumn("sobrenome")
          .execute());
  }
}