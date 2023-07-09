create keyspace plankton with replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

create table plankton.users
(
    id           uuid,
    email        text,
    is_active    boolean,
    access_token text,
    full_name    text,
    password     text,
    primary key ((id, email), is_active)
)
    with clustering order by (is_active desc)
     and comment = 'Пользователи';

