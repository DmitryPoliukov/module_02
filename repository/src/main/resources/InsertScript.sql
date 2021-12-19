insert into gift_certificate (id, name, description, price, duration, create_date, last_update_date)
values (1, 'certificate1', 'cool cerf', 1.33, 30, 256, 1235649884),
       (2, 'certificate2', 'certific description', 2.33, 30, 256, 98765432);

insert into tag (id, name)
values (1, 'tag1'),
       (2, 'tag2'),
       (3, 'tag3'),
       (4, 'tag4');

insert into gift_certificate_m2m_tag (gift_certificate_id, tag_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 1);


