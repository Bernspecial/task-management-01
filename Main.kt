import java.util.Scanner

// Data class to represent a task
data class Task(
    val id: Int,
    var title: String,
    var description: String,
    var dueDate: String,
    var isDone: Boolean = false
)

// Global scanner for input
val scanner = Scanner(System.`in`)

// Mutable collection to hold tasks
val tasks = mutableListOf<Task>()

// Function to safely read input
fun readInput(prompt: String): String {
    print(prompt)
    return scanner.nextLine()
}

// Function to add a new task
fun addTask() {
    println("\n--- Add New Task ---")
    val title = readInput("Enter task title: ")
    val description = readInput("Enter task description: ")
    val dueDate = readInput("Enter due date (e.g., 2025-09-30): ")
    val task = Task(tasks.size + 1, title, description, dueDate)
    tasks.add(task)
    println("Task added successfully!")
}

// Function to list all tasks
fun listTasks() {
    println("\n--- Task List ---")
    if (tasks.isEmpty()) {
        println("No tasks found.")
    } else {
        for (task in tasks) {
            val status = if (task.isDone) "[Done]" else "[Pending]"
            println("${task.id}. ${task.title} $status")
            println("   Description: ${task.description}")
            println("   Due: ${task.dueDate}")
        }
    }
}

// Function to mark a task as done
fun markTaskDone() {
    println("\n--- Mark Task as Done ---")
    val id = readInput("Enter task ID: ").toIntOrNull()
    if (id != null && id in 1..tasks.size) {
        val task = tasks[id - 1]
        task.isDone = true
        println("Task '${task.title}' marked as done!")
    } else {
        println("Invalid task ID.")
    }
}

// Function to delete a task
fun deleteTask() {
    println("\n--- Delete Task ---")
    val id = readInput("Enter task ID: ").toIntOrNull()
    if (id != null && id in 1..tasks.size) {
        val task = tasks.removeAt(id - 1)
        println("Task '${task.title}' deleted successfully!")
    } else {
        println("Invalid task ID.")
    }
}

// Function to filter tasks
fun filterTasks() {
    println("\n--- Filter Tasks ---")
    println("1. Show only pending")
    println("2. Show only completed")
    print("Choose: ")

    when (readLine()) {
        "1" -> tasks.filter { !it.isDone }.forEach { println("${it.id}. ${it.title} [Pending]") }
        "2" -> tasks.filter { it.isDone }.forEach { println("${it.id}. ${it.title} [Done]") }
        else -> println("Invalid choice.")
    }
}

// Main program with a menu
fun main() {
    while (true) {
        println("\n====== Task Manager ======")
        println("1. Add task")
        println("2. List tasks")
        println("3. Mark task as done")
        println("4. Delete task")
        println("5. Filter tasks")
        println("6. Exit")
        print("Choose an option: ")

        when (readLine()) {
            "1" -> addTask()
            "2" -> listTasks()
            "3" -> markTaskDone()
            "4" -> deleteTask()
            "5" -> filterTasks()
            "6" -> {
                println("Goodbye!")
                return
            }
            else -> println("Invalid choice. Try again.")
        }
    }
}