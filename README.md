# quarkus-trial-todolist

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## API List
http://localhost:8080/q/dev-ui/endpoints

## Setup application-dev.properties
This file is not pushed, but must be created with own settings
```
# --- datasource ---
quarkus.datasource.db-kind=
quarkus.datasource.jdbc.url=
quarkus.datasource.username=
quarkus.datasource.password=

# --- hibernate schema generation ---
quarkus.hibernate-orm.database.generation=update
# Options:
# update          = auto update schema based on entities
# drop-and-create = drop and recreate schema on every run (clean dev mode)

# --- logging ---
quarkus.hibernate-orm.log.sql=true

quarkus.annotation-processor.lombok.enabled=true
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.