create table CLUB_MEMBERS
(
    MEMBER_ID             int auto_increment
        primary key,
    FULL_NAME             varchar(255)            not null,
    DOB                   date                    null,
    ADDRESS               text                    null,
    EMAIL                 varchar(255)            null,
    PHONE                 varchar(20)             null,
    MEMBERSHIP_START_DATE date                    not null,
    MEMBERSHIP_TYPE       enum ('Regular', 'VIP') null,
    SUBSCRIPTION_FEE      decimal(10, 2)          null
);

create table FOOD_AND_DRINKS
(
    ITEM_ID   int auto_increment
        primary key,
    ITEM_NAME varchar(255)           null,
    CATEGORY  enum ('Food', 'Drink') null,
    PRICE     decimal(10, 2)         null
);

create table HORSE_CATEGORIES
(
    CATEGORY_ID   int auto_increment
        primary key,
    CATEGORY_NAME varchar(100) not null
);

create table HORSES
(
    HORSE_ID        int auto_increment
        primary key,
    NAME            varchar(255)            not null,
    BIRTH_DATE      date                    not null,
    GENDER          enum ('Male', 'Female') not null,
    COLOR           varchar(100)            null,
    HEIGHT          decimal(5, 2)           null,
    WEIGHT          decimal(5, 2)           null,
    BIRTHMARKS      text                    null,
    CATEGORY_ID     int                     null,
    SIRE_ID         int                     null,
    DAM_ID          int                     null,
    HANDICAP_RATING decimal(4, 2)           null,
    constraint horses_ibfk_1
        foreign key (CATEGORY_ID) references HORSE_CATEGORIES (CATEGORY_ID),
    constraint horses_ibfk_2
        foreign key (SIRE_ID) references HORSES (HORSE_ID),
    constraint horses_ibfk_3
        foreign key (DAM_ID) references HORSES (HORSE_ID)
);

create index CATEGORY_ID
    on HORSES (CATEGORY_ID);

create index DAM_ID
    on HORSES (DAM_ID);

create index SIRE_ID
    on HORSES (SIRE_ID);

create table JOCKEYS
(
    JOCKEY_ID        int auto_increment
        primary key,
    FULL_NAME        varchar(255)   not null,
    AGE              int            null,
    EXPERIENCE_YEARS int            null,
    WEIGHT           decimal(5, 2)  null,
    HEIGHT           decimal(5, 2)  null,
    EXPERIENCE       decimal(10, 2) null
);

create table ORDERS
(
    ORDER_ID     int auto_increment
        primary key,
    MEMBER_ID    int            null,
    ORDER_DATE   date           null,
    TOTAL_AMOUNT decimal(10, 2) null,
    constraint orders_ibfk_1
        foreign key (MEMBER_ID) references CLUB_MEMBERS (MEMBER_ID)
);

create index MEMBER_ID
    on ORDERS (MEMBER_ID);

create table ORDER_DETAILS
(
    ORDER_DETAIL_ID int auto_increment
        primary key,
    ORDER_ID        int            null,
    ITEM_ID         int            null,
    QUANTITY        int            null,
    ITEM_PRICE      decimal(10, 2) null,
    constraint order_details_ibfk_1
        foreign key (ORDER_ID) references ORDERS (ORDER_ID),
    constraint order_details_ibfk_2
        foreign key (ITEM_ID) references FOOD_AND_DRINKS (ITEM_ID)
);

create index ITEM_ID
    on ORDER_DETAILS (ITEM_ID);

create index ORDER_ID
    on ORDER_DETAILS (ORDER_ID);

create table ORGANIZERS
(
    ORGANIZER_ID int auto_increment
        primary key,
    FULL_NAME    varchar(255) null,
    ROLE         varchar(100) null,
    CONTACT      varchar(20)  null
);

create table PARTY_BOOKINGS
(
    BOOKING_ID  int auto_increment
        primary key,
    MEMBER_ID   int            null,
    EVENT_DATE  date           null,
    VENUE       varchar(255)   null,
    GUEST_COUNT int            null,
    TOTAL_COST  decimal(10, 2) null,
    constraint party_bookings_ibfk_1
        foreign key (MEMBER_ID) references CLUB_MEMBERS (MEMBER_ID)
);

create index MEMBER_ID
    on PARTY_BOOKINGS (MEMBER_ID);

create table PAYMENTS
(
    PAYMENT_ID   int auto_increment
        primary key,
    MEMBER_ID    int                                null,
    PAYMENT_DATE datetime default CURRENT_TIMESTAMP null,
    AMOUNT       decimal(10, 2)                     null,
    PAYMENT_TYPE enum ('Card', 'Cash', 'Online')    null,
    constraint payments_ibfk_1
        foreign key (MEMBER_ID) references CLUB_MEMBERS (MEMBER_ID)
);

create index MEMBER_ID
    on PAYMENTS (MEMBER_ID);

create table RACES
(
    RACE_ID      int auto_increment
        primary key,
    RACE_NAME    varchar(255) not null,
    LOCATION     varchar(255) null,
    RACE_DATE    date         not null,
    RACE_TIME    time         not null,
    CATEGORY_ID  int          null,
    ANNOUNCED_BY int          null,
    constraint races_ibfk_1
        foreign key (CATEGORY_ID) references HORSE_CATEGORIES (CATEGORY_ID),
    constraint races_ibfk_2
        foreign key (ANNOUNCED_BY) references ORGANIZERS (ORGANIZER_ID)
);

create table ANNOUNCEMENTS
(
    ANNOUNCEMENT_ID   int auto_increment
        primary key,
    RACE_ID           int      null,
    ANNOUNCEMENT_TEXT text     null,
    ANNOUNCEMENT_DATE datetime null,
    constraint announcements_ibfk_1
        foreign key (RACE_ID) references RACES (RACE_ID)
);

create index RACE_ID
    on ANNOUNCEMENTS (RACE_ID);

create table BETTING
(
    BET_ID      int auto_increment
        primary key,
    RACE_ID     int                  null,
    HORSE_ID    int                  null,
    MEMBER_ID   int                  null,
    BET_AMOUNT  decimal(10, 2)       null,
    ODDS        decimal(5, 2)        null,
    BET_OUTCOME enum ('Win', 'Loss') null,
    PAYOUT      decimal(10, 2)       null,
    constraint betting_ibfk_1
        foreign key (RACE_ID) references RACES (RACE_ID),
    constraint betting_ibfk_2
        foreign key (HORSE_ID) references HORSES (HORSE_ID),
    constraint betting_ibfk_3
        foreign key (MEMBER_ID) references CLUB_MEMBERS (MEMBER_ID)
);

create index HORSE_ID
    on BETTING (HORSE_ID);

create index MEMBER_ID
    on BETTING (MEMBER_ID);

create index RACE_ID
    on BETTING (RACE_ID);

create table BETTING_PAYOUTS
(
    PAYOUT_ID    int auto_increment
        primary key,
    BET_ID       int                                null,
    AMOUNT       decimal(10, 2)                     null,
    PAYMENT_DATE datetime default CURRENT_TIMESTAMP null,
    constraint betting_payouts_ibfk_1
        foreign key (BET_ID) references BETTING (BET_ID)
);

create index BET_ID
    on BETTING_PAYOUTS (BET_ID);

create index ANNOUNCED_BY
    on RACES (ANNOUNCED_BY);

create index CATEGORY_ID
    on RACES (CATEGORY_ID);

create table RACES_WON
(
    RACE_WIN_ID int auto_increment
        primary key,
    HORSE_ID    int null,
    RACE_ID     int null,
    POSITION    int null,
    constraint races_won_ibfk_1
        foreign key (HORSE_ID) references HORSES (HORSE_ID),
    constraint races_won_ibfk_2
        foreign key (RACE_ID) references RACES (RACE_ID)
);

create index HORSE_ID
    on RACES_WON (HORSE_ID);

create index RACE_ID
    on RACES_WON (RACE_ID);

create table RACE_PARTICIPANTS
(
    PARTICIPATION_ID  int auto_increment
        primary key,
    RACE_ID           int           null,
    HORSE_ID          int           null,
    JOCKEY_ID         int           null,
    STARTING_POSITION int           null,
    HANDICAP          decimal(4, 2) null,
    constraint race_participants_ibfk_1
        foreign key (RACE_ID) references RACES (RACE_ID),
    constraint race_participants_ibfk_2
        foreign key (HORSE_ID) references HORSES (HORSE_ID),
    constraint race_participants_ibfk_3
        foreign key (JOCKEY_ID) references JOCKEYS (JOCKEY_ID)
);

create index HORSE_ID
    on RACE_PARTICIPANTS (HORSE_ID);

create index JOCKEY_ID
    on RACE_PARTICIPANTS (JOCKEY_ID);

create index RACE_ID
    on RACE_PARTICIPANTS (RACE_ID);

create table RACE_RESULTS
(
    RESULT_ID  int auto_increment
        primary key,
    RACE_ID    int  null,
    HORSE_ID   int  null,
    POSITION   int  null,
    TIME_TAKEN time null,
    constraint race_results_ibfk_1
        foreign key (RACE_ID) references RACES (RACE_ID),
    constraint race_results_ibfk_2
        foreign key (HORSE_ID) references HORSES (HORSE_ID)
);

create index HORSE_ID
    on RACE_RESULTS (HORSE_ID);

create index RACE_ID
    on RACE_RESULTS (RACE_ID);

create table ROLES
(
    ROLE_ID     int auto_increment
        primary key,
    ROLE_NAME   varchar(100) not null,
    DESCRIPTION text         null,
    PERMISSIONS json         null
);

create table TICKETS
(
    TICKET_ID   int auto_increment
        primary key,
    RACE_ID     int                     null,
    MEMBER_ID   int                     null,
    TICKET_TYPE enum ('General', 'VIP') null,
    PRICE       decimal(10, 2)          null,
    constraint tickets_ibfk_1
        foreign key (RACE_ID) references RACES (RACE_ID),
    constraint tickets_ibfk_2
        foreign key (MEMBER_ID) references CLUB_MEMBERS (MEMBER_ID)
);

create index MEMBER_ID
    on TICKETS (MEMBER_ID);

create index RACE_ID
    on TICKETS (RACE_ID);

create table USERS
(
    USER_ID       int auto_increment
        primary key,
    FULL_NAME     varchar(255)                                          null,
    EMAIL         varchar(255)                                          not null,
    USERNAME      varchar(255)                                          not null,
    PASSWORD_HASH varchar(255)                                          not null,
    PHONE_NUMBER  varchar(20)                                           null,
    IS_ADMIN      tinyint(1)                  default 0                 null,
    ROLE_ID       int                                                   null,
    STATUS        enum ('Active', 'Inactive') default 'Active'          null,
    CREATED_AT    datetime                    default CURRENT_TIMESTAMP null,
    UPDATED_AT    datetime                    default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint EMAIL
        unique (EMAIL),
    constraint USERNAME
        unique (USERNAME),
    constraint users_ibfk_1
        foreign key (ROLE_ID) references ROLES (ROLE_ID)
);

create table ADMINS
(
    ADMIN_ID      int auto_increment
        primary key,
    USER_ID       int                            null,
    ASSIGNED_DATE date                           null,
    ADMIN_TYPE    enum ('SuperAdmin', 'Manager') null,
    constraint admins_ibfk_1
        foreign key (USER_ID) references USERS (USER_ID)
);

create index USER_ID
    on ADMINS (USER_ID);

create table AUDIT_TRAIL
(
    AUDIT_ID         int auto_increment
        primary key,
    USER_ID          int                                null,
    ACTION_PERFORMED text                               not null,
    TABLE_AFFECTED   varchar(100)                       null,
    RECORD_ID        int                                null,
    ACTION_TIMESTAMP datetime default CURRENT_TIMESTAMP null,
    IP_ADDRESS       varchar(50)                        null,
    REMARKS          text                               null,
    constraint audit_trail_ibfk_1
        foreign key (USER_ID) references USERS (USER_ID)
);

create index USER_ID
    on AUDIT_TRAIL (USER_ID);

create table LOGINS
(
    LOGIN_ID         int auto_increment
        primary key,
    USER_ID          int                                null,
    LOGIN_TIMESTAMP  datetime default CURRENT_TIMESTAMP null,
    LOGOUT_TIMESTAMP datetime                           null,
    IP_ADDRESS       varchar(50)                        null,
    DEVICE_DETAILS   varchar(255)                       null,
    constraint logins_ibfk_1
        foreign key (USER_ID) references USERS (USER_ID)
);

create index USER_ID
    on LOGINS (USER_ID);

create index ROLE_ID
    on USERS (ROLE_ID);

