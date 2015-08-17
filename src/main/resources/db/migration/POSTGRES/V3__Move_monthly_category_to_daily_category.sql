INSERT INTO parent_outcome_category (parent_outcome_category_id, category_name, created_at, updated_at, version) VALUES
  (12, '固定費', '2015-03-14 15:00:00', '2015-03-14 15:00:00', 0);

INSERT INTO daily_outcome_category (daily_outcome_category_id, category_name, parent_outcome_category_id, created_at, updated_at, version)
VALUES
  (70, '家賃', 12, '2015-03-14 15:00:00', '2015-03-14 15:00:00', 0),
  (71, '電気', 12, '2015-03-14 15:00:00', '2015-03-14 15:00:00', 0),
  (72, 'ガス', 12, '2015-03-14 15:00:00', '2015-03-14 15:00:00', 0),
  (73, '水道', 12, '2015-03-14 15:00:00', '2015-03-14 15:00:00', 0),
  (74, '電話', 12, '2015-03-14 15:00:00', '2015-03-14 15:00:00', 0),
  (75, 'インターネット', 12, '2015-03-14 15:00:00', '2015-03-14 15:00:00', 0),
  (76, '新聞', 12, '2015-03-14 15:00:00', '2015-03-14 15:00:00', 0),
  (77, '保険', 12, '2015-03-14 15:00:00', '2015-03-14 15:00:00', 0),
  (78, '貯金', 12, '2015-03-14 15:00:00', '2015-03-14 15:00:00', 0),
  (79, 'その他', 12, '2015-03-14 15:00:00', '2015-03-14 15:00:00', 0);

INSERT INTO daily_outcome (
  amount,
  created_at,
  is_waste,
  outcome_date,
  outcome_name,
  payment,
  quantity,
  remarks,
  updated_at,
  version,
  daily_outcome_category_id,
  updated_by,
  user_id)

  SELECT
    x.amount,
    x.created_at,
    FALSE,
    x.outcome_date,
    x.outcome_name,
    'CASH',
    1,
    x.remarks,
    x.updated_at,
    0,
    (SELECT daily_outcome_category_id
     FROM daily_outcome_category
     WHERE category_name = y.category_name),
    x.updated_by,
    (SELECT user_id
     FROM "user"
     WHERE family_id = x.family_id
     ORDER BY user_id ASC
     LIMIT 1)
  FROM monthly_outcome AS x
    LEFT JOIN monthly_outcome_category AS y
      ON x.monthly_outcome_category_id = y.monthly_outcome_category_id;