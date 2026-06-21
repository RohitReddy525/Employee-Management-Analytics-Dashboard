import pandas as pd
import plotly.express as px
from database import get_connection

conn = get_connection()

df = pd.read_sql("SELECT * FROM employees", conn)

dept_count = df["department"].value_counts().reset_index()
dept_count.columns = ["Department", "Count"]

fig = px.bar(
    dept_count,
    x="Department",
    y="Count",
    title="Employees by Department"
)

fig.show()

conn.close()