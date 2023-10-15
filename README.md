# Time Tracker Application

## Usage

### Running the application
Package the application using the command
`mvn package`
Run the generated archive file inside the target folder using the command
`java -jar <archive-file-name.jar>`

Start using the application using the commands stated below
### Start logging time
`start <task-name>` <br>
`start <task-name> <category-name>`

If the task name already exists then this will be a no-op

### Stop logging time
`stop <task-name>`

### Show tasks
`report tasks`
Show the running tasks and the time spent on each of them

### Show categories
`report categories`
Show the categories and the time spent on each of them

### Restart
`restart`
Removes all the tasks and starts again

### Exit the time tracker app
`exit`
Exits the CLI application

