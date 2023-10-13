    create table apply (
        apply_id bigint not null auto_increment,
        job_listing_id bigint,
        user_id bigint,
        primary key (apply_id)
    ) engine=InnoDB;

    create table company (
        company_id bigint not null auto_increment,
        country varchar(255),
        name varchar(255),
        region varchar(255),
        primary key (company_id)
    ) engine=InnoDB;

    create table job_listing (
        company_id bigint,
        job_listing_id bigint not null auto_increment,
        reward bigint,
        description varchar(1000),
        position varchar(255),
        tech_stack varchar(255),
        primary key (job_listing_id)
    ) engine=InnoDB;

    create table user (
        user_id bigint not null auto_increment,
        name varchar(255),
        primary key (user_id)
    ) engine=InnoDB;

    alter table apply
       add constraint FKem9sxob6ou2irulauvttsbw1r
       foreign key (job_listing_id)
       references job_listing (job_listing_id);

    alter table apply
       add constraint FKfp0eaj9wr8aj6v5qlfd9luxw1
       foreign key (user_id)
       references user (user_id);

    alter table job_listing
       add constraint FKexjgideqcr08f26msh7ju51m3
       foreign key (company_id)
       references company (company_id);

    insert into company (name, country, region) values ('네이버', '한국', '판교'), ('카카오', '한국', '판교');

    insert into user (name) values ('이준규');

