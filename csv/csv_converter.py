import requests
from ics import Calendar
import pandas as pd
from datetime import datetime, time
import re

ical_url = input("Enter ical link: ")

response = requests.get(ical_url)
calendar = Calendar(response.text)

events_data = []
for event in calendar.events:
    start = event.begin.datetime
    end = event.end.datetime
    duration = end - start

    base_time = datetime.combine(datetime.today(), datetime.min.time())
    duration_as_datetime = base_time + duration

    module = re.search(r'\bIOT\w*\b', event.name)

    events_data.append({
        "Event Name": event.name,
        "Module": module.group(), 
        "Activity": "",
        "Date": start.strftime("%d-%m-%Y"),
        "Start Time": start.strftime("%H:%M"),
        "Duration": duration_as_datetime.strftime("%H:%M"),
        "Description": ""
    })

df = pd.DataFrame(events_data)
df.to_csv("calendar_events.csv", index=False)

print("✅ Exported to calendar_events.csv")

