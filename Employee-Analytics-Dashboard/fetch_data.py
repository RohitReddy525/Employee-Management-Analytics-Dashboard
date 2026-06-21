import pandas as pd
from database import get_connection

conn = get_connection()

query = "SELECT * FROM employees"

df = pd.read_sql(query, conn)

print(df)

conn.close()