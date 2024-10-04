<!DOCTYPE html>
<html lang="en">
<body>

<h1>Task Management Console Application</h1>

<p>This is a simple console-based task management application built with Spring 5, Hibernate 5, and MySQL 5.7. The application allows users to create, update, delete, and view tasks.</p>

<h2>Table of Contents</h2>
<ul>
    <li><a href="#features">Features</a></li>
    <li><a href="#technologies-used">Technologies Used</a></li>
    <li><a href="#installation">Installation</a></li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#configuration">Configuration</a></li>
</ul>

<h2 id="features">Features</h2>
<ul>
    <li>Create a new task</li>
    <li>Update existing tasks</li>
    <li>Delete tasks</li>
    <li>View all tasks</li>
    <li>View a specific task by ID</li>
</ul>

<h2 id="technologies-used">Technologies Used</h2>
<ul>
    <li><strong>Java 8</strong>: Programming language</li>
    <li><strong>Spring 5</strong>: Framework for building the application</li>
    <li><strong>Hibernate 5</strong>: ORM for database operations</li>
    <li><strong>MySQL 5.7</strong>: Database to store task information</li>
    <li><strong>Maven</strong>: Project management and build tool</li>
</ul>

<h2 id="installation">Installation</h2>
<ol>
    <li><strong>Clone the repository:</strong>
        <pre><code>git clone https://github.com/Prabhav-saini/ToDoList.git</code></pre>
    </li>
    <li><strong>Set up MySQL:</strong>
        <ul>
            <li>Ensure that MySQL 5.7 is installed and running.</li>
            <li>Create a database for the application:
                <pre><code>CREATE DATABASE todolist;</code></pre>
            </li>
        </ul>
    </li>
    <li><strong>Update database configuration:</strong>
        <p>Modify the <code>ToDoConfig</code> class to set your MySQL credentials:</p>
        <pre><code>config.setJdbcUrl("jdbc:mysql://localhost:3306/todolist");
config.setUsername("your_username");
config.setPassword("your_password");</code></pre>
    </li>
    <li><strong>Build the project:</strong>
        <pre><code>mvn clean install</code></pre>
    </li>
    <li><strong>Run the application:</strong>
        <p>You can run the main class from your IDE or through the command line:</p>
        <pre><code>java -cp target/task-management-1.0-SNAPSHOT.jar org.example.MainClass</code></pre>
    </li>
</ol>

<h2 id="usage">Usage</h2>
<p>After running the application, follow the console prompts to create, update, delete, or view tasks.</p>
<ul>
    <li><strong>Create Task:</strong> Enter task details when prompted.</li>
    <li><strong>View All Tasks:</strong> Select the option to view all tasks.</li>
    <li><strong>Update Task:</strong> Provide the task ID and new details.</li>
    <li><strong>Delete Task:</strong> Enter the task ID of the task you wish to delete.</li>
</ul>

<h2 id="configuration">Configuration</h2>
<p>The application is configured using Spring’s Java-based configuration. Key components include:</p>
<ul>
    <li><strong>Data Source:</strong> Configured using HikariCP for efficient connection pooling.</li>
    <li><strong>Session Factory:</strong> Utilizes Hibernate to manage sessions and transactions.</li>
    <li><strong>Transaction Management:</strong> Enabled with Spring's <code>HibernateTransactionManager</code>.</li>
</ul>
<p>For detailed configurations, see the <code>ToDoConfig</code> class in the <code>org.example.Configuration</code> package.</p>

<h3>Example Configuration</h3>
<pre><code>@Bean
public DataSource dataSource() {
    HikariConfig config = new HikariConfig();
    config.setDriverClassName("com.mysql.cj.jdbc.Driver");
    config.setJdbcUrl("jdbc:mysql://localhost:3306/todolist");
    config.setUsername("your_username");
    config.setPassword("your_password");
    return new HikariDataSource(config);
}</code></pre>

</body>
</html>
