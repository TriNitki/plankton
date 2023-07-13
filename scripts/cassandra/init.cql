create keyspace plankton with replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

create type plankton.replay (
    replay_type text,
    start_date date,
    end_date date,
    dates set<date>
    );

create table plankton.event_groups
(
    event_group_id uuid primary key,
    date           date,
    description    text,
    owner_id       uuid,
    replay         frozen<replay>,
    section_id     uuid,
    time           time
)
    with comment = 'Группы ивентов';

create table plankton.event_groups_by_section
(
    section_id      uuid primary key,
    event_group_ids set<uuid>
)
    with comment = 'Идентификаторы групп по идентификатору секции';

create table plankton.events
(
    owner_id       uuid,
    date           date,
    time           time,
    description    text,
    event_group_id uuid,
    event_id       uuid,
    primary key (owner_id, date, time)
)
    with comment = 'Ивенты';

create table plankton.id_by_email
(
    email text primary key,
    id    uuid
)
    with comment = 'Поиск идентификатора по почте';

create table plankton.sections
(
    owner_id    uuid primary key,
    access_type text,
    is_public   boolean,
    section_id  uuid,
    title       text,
    url         text
)
    with comment = 'Секции пользователей';

create table plankton.users
(
    id           uuid primary key,
    access_token text,
    email        text,
    full_name    text,
    is_active    boolean,
    password     text
)
    with comment = 'Пользователи';

