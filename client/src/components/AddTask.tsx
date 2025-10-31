import axios from "axios";
import React, { useState } from "react";

type AddTaskProps = {
    onSuccess?: () => void; // 👈 optional close handler
};

const AddTask = ({ onSuccess }: AddTaskProps) => {
    const [task, setTask] = useState({
        taskName: "",
        taskDescription: "",
    });


    function handleChange(e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) {
        const { name, value } = e.target;
        setTask((prev) => ({ ...prev, [name]: value }));
    }

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (!task.taskName.trim() || !task.taskDescription.trim()) {
            alert("Please fill out both fields!");
            return;
        }

        try {
            axios.post("http://localhost:8080/api/v1/tasks", task).then((res) => {
                console.log(res);
                setTask({ taskName: "", taskDescription: "" });
            }).catch((err) => {
                console.log(err);
            })
            if (onSuccess) onSuccess(); // 👈 call parent handler
        } catch (err) {
            console.log(err);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="flex flex-col gap-3 w-full">
            <h2 className="text-lg font-semibold mb-2 text-center">Add Task</h2>

            <input
                name="taskName"
                className="bg-gray-100 w-full p-2 rounded text-sm focus:outline-none focus:ring focus:ring-gray-400"
                placeholder="Task Name"
                value={task.taskName}
                onChange={handleChange}
            />

            <textarea
                name="taskDescription"
                placeholder="Task Description"
                className="w-full min-h-[100px] max-h-[300px] p-2 bg-gray-100 rounded-md text-sm focus:outline-none focus:ring focus:ring-gray-400 resize-none overflow-auto"
                value={task.taskDescription}
                onChange={handleChange}
            ></textarea>

            <button
                type="submit"
                className="text-xs bg-black px-3 py-1 rounded border-2 border-black text-white font-medium hover:bg-white  hover:text-black hover:border-2 hover:border-black transition shadow-md hover:shadow-lg"
            >
                Create Task
            </button>
        </form>
    );
};

export default AddTask;
