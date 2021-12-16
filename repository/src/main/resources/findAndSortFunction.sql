CREATE OR REPLACE FUNCTION find_and_sort_certificates(tagName character varying,
                                                      queryPart character varying,
                                                      sortBy character varying,
                                                      descending boolean)
              RETURNS SETOF certificate
              LANGUAGE plpgsql ///уточнить для твоей бд
              AS $$
BEGIN
    IF tagName IS NOT NULL AND queryPart IS NOT NULL THEN - ищем по тегу и части имени или дескрипшена
RETURN QUERY SELECT .......
                     WHERE name_tag = tagName and (POSITION($2 IN gift_name)>0 OR POSITION($2 IN description)>0)
                     /////тут пояснение про знак $ https://www.postgresql.org/docs/8.1/sql-syntax.html#:~:text=A%20dollar%20sign%20(%24)%20followed,a%20dollar%2Dquoted%20string%20constant.
             ORDER BY
                     CASE WHEN sortBy='name' AND descending IS TRUE THEN gift_name END DESC,
    CASE WHEN sortBy=....
    -можно задать сортировку по дефолту если это не имя и не дата, например по ошибке что-то ввели, тогда сортировать по имени
    ELSIF tagName IS NOT NULL THEN
    RETURN QUERY SELECT .....
                     ELSIF queryPart IS NOT NULL THEN
                     RETURN QUERY SELECT ....
                                      ELSE
                                      RETURN QUERY SELECT ....
                                                       END IF;
END;
$$;