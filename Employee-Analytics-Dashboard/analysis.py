import pandas as pd
from database import get_connection

conn = get_connection()

df = pd.read_sql("SELECT * FROM employees", conn)

print("\nTotal Employees:", len(df))

print("\nAverage Salary:")
print(df["salary"].mean())

print("\nHighest Salary:")
print(df["salary"].max())

print("\nLowest Salary:")
print(df["salary"].min())

print("\nDepartment Count:")
print(df["department"].value_counts())

conn.close()