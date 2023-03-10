CREATE TABLE ORDERS
(
    ID            bigint not null,
    CREATION      timestamp(6),
    RESTAURANT_ID bigint not null,
    primary key (ID),
    CONSTRAINT ORDER_RESTAURANT_FK FOREIGN KEY (RESTAURANT_ID) REFERENCES RESTAURANTS (ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ORDER_ITEMS
(
    ID       bigint not null,
    DISH_ID  bigint not null,
    ORDER_ID bigint not null,
    primary key (ID),
    CONSTRAINT ORDER_ITEM_ORDER_FK FOREIGN KEY (ORDER_ID) REFERENCES ORDERS (ID) ON DELETE CASCADE
);