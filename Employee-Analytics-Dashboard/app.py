import streamlit as st
import pandas as pd
import plotly.express as px
from database import get_connection

st.title("Employee Analytics Dashboard")
st.caption("Interactive dashboard for employee data analysis and visualization")

conn = get_connection()
df = pd.read_sql("SELECT * FROM employees", conn)
col1, col2, col3 = st.columns(3)

with col1:
    st.metric("Total Employees", len(df))

with col2:
    st.metric("Average Salary", round(df["salary"].mean(), 2))

with col3:
    st.metric("Highest Salary", df["salary"].max())

st.subheader("Employee Data")



department = st.selectbox(
    "Select Department",
    ["All"] + list(df["department"].unique())
)
if department != "All":
    filtered_df = df[df["department"] == department]
else:
    filtered_df = df


dept_count = df["department"].value_counts().reset_index()

dept_count.columns = ["Department", "Count"]

min_salary = int(df["salary"].min())
max_salary = int(df["salary"].max())

salary_range = st.slider(
    "Select Salary Range",
    min_salary,
    max_salary,
    (min_salary, max_salary)
)
filtered_df = filtered_df[
    (filtered_df["salary"] >= salary_range[0]) &
    (filtered_df["salary"] <= salary_range[1])
]
search = st.text_input("Search Employee by Name")

if search:
    filtered_df = filtered_df[
        filtered_df["name"].str.contains(search, case=False)
    ]
    st.subheader("Filtered Employee Data")

st.dataframe(filtered_df)
fig = px.pie(
    dept_count,
    values="Count",
    names="Department",
    title="Employee Distribution by Department"
)

avg_salary = df.groupby("department")["salary"].mean().reset_index()

fig3 = px.bar(
    avg_salary,
    x="department",
    y="salary",
    title="Average Salary by Department"
)
st.plotly_chart(fig3, use_container_width=True)

csv = filtered_df.to_csv(index=False)

st.download_button(
    "Download Filtered Data",
    csv,
    "employees.csv",
    "text/csv"
)

st.subheader("Department Statistics")

dept_stats = df.groupby("department").agg(
    Employee_Count=("id", "count"),
    Average_Salary=("salary", "mean"),
    Highest_Salary=("salary", "max"),
    Lowest_Salary=("salary", "min")
).reset_index()

st.dataframe(dept_stats)

st.plotly_chart(fig, use_container_width=True)

st.subheader("Top 5 Highest Paid Employees")

top5 = df.sort_values(by="salary", ascending=False).head(5)

st.dataframe(top5)

st.markdown("---")
st.write("Developed by Rohit Bojjam")
st.write("Employee Analytics Dashboard using Python, MySQL, Pandas, Streamlit and Plotly")
conn.close()