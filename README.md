# Time Tracker Application

## Usage
### Start logging time
`time-tracker start <task-name>` <br>
`time-tracker start <task-name> <category-name>`

If the task name already exists then this will be a no-op

### Stop logging time
`time-tracker stop <task-name>`

### Show tasks
`time-tracker report task`
Show the running tasks and the time spent on each of them

### Show categories
`time-tracker report category`
Show the categories and the time spent on each of them

### Restart
`time-tracker restart`
Deletes all the tasks and starts again

### Exit the time tracker app
`time-tracker exit`
Exits the CLI application

