// Fetch all tasks from the server
const fetchTasks = async () => {
    const response = await fetch('/todos');
    const tasks = await response.json();
    return tasks;
};

// Fetch all completed tasks from the server
const fetchCompletedTasks = async () => {
    const response = await fetch('/completed');
    const tasks = await response.json();
    return tasks;
};

// Create task list item
const createTaskItem = (task, index) => {
    const li = document.createElement('li');
    li.dataset.index = task.id;
    li.textContent = task.task;
    li.classList.add('task-item');

    const buttonEdit = document.createElement('button');
    buttonEdit.textContent = 'Edit';
    buttonEdit.classList.add('edit-task');

    const buttonDelete = document.createElement('button');
    buttonDelete.textContent = 'Delete';
    buttonDelete.classList.add('delete-task');

    const buttonComplete = document.createElement('button');
    buttonComplete.textContent = 'Complete';
    buttonComplete.classList.add('complete-task');

    li.appendChild(buttonEdit);
    li.appendChild(buttonDelete);
    li.appendChild(buttonComplete);

    return li;
};

// Create completed task list item
const createCompletedTaskItem = (task) => {
    const li = document.createElement('li');
    li.classList.add('completed-task-item');

    const taskText = document.createElement('span');
    taskText.textContent = task.task;
    li.appendChild(taskText);

    return li;
};

// Populate task list
const populateTasks = async () => {
    const todoList = document.getElementById('todo-list-items');
    todoList.innerHTML = '';
    const tasks = await fetchTasks();
    tasks.forEach((task, index) => {
        todoList.appendChild(createTaskItem(task, index));
    });

    const completedTasksList = document.getElementById('completed-tasks');
    completedTasksList.innerHTML = '';
    const completedTasks = await fetchCompletedTasks();
    completedTasks.forEach((task) => {
        completedTasksList.appendChild(createCompletedTaskItem(task));
    });
};

// Function to add a new task
const addTask = async () => {
    const taskInput = document.getElementById('taskInput');
    const newTask = { task: taskInput.value };
    await fetch('/todos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newTask),
    });
    taskInput.value = '';
    populateTasks();
};

// Function to mark a task as completed and move to completed list
const markTaskAsCompleted = async (taskId) => {
    const taskText = document.querySelector(`li[data-index='${taskId}']`).textContent.trim();

    // Move task to completed list
    await fetch(`/todos/${taskId}`, {
        method: 'DELETE',
    });

    // Add task to completed list
    await fetch(`/completed`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ task: taskText }),
    });

    // Populate tasks again
    populateTasks();
};

// Event listener for adding a task
document.querySelector('.addTask').addEventListener('click', addTask);

// Event listener for editing a task
document.addEventListener('click', async (e) => {
    if (e.target && e.target.classList.contains('edit-task')) {
        const li = e.target.parentElement;
        const taskId = li.dataset.index;
        const taskInput = document.getElementById('taskInput');
        taskInput.value = li.textContent.trim();

        await fetch(`/todos/${taskId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ task: taskInput.value }),
        });

        populateTasks();
    }
});

// Event listener for deleting a task
document.addEventListener('click', async (e) => {
    if (e.target && e.target.classList.contains('delete-task')) {
        const taskId = e.target.parentElement.dataset.index;

        await fetch(`/todos/${taskId}`, {
            method: 'DELETE',
        });

        populateTasks();
    }
});

// Event listener for marking a task as completed
document.addEventListener('click', async (e) => {
    if (e.target && e.target.classList.contains('complete-task')) {
        const taskId = e.target.parentElement.dataset.index;
        await markTaskAsCompleted(taskId);
    }
});

// Initial population of tasks
populateTasks();
