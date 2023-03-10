CREATE TABLE DISHES
(
    ID            bigint    not null,
    DESCRIPTION   varchar(255),
    NAME          varchar(255),
    PRICE         float(53) not null,
    RESTAURANT_ID bigint    not null,
    primary key (ID),
    CONSTRAINT DISH_RESTAURANT_FK FOREIGN KEY (RESTAURANT_ID) REFERENCES RESTAURANTS (ID) ON DELETE CASCADE ON UPDATE CASCADE
);