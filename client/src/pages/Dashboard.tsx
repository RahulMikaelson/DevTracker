import axios from "axios";
import { useEffect, useRef, useState } from "react";
import type { Task } from "../types/Task";
import TaskCard from "../components/TaskCard";
import AddTask from "../components/AddTask";

const Dashboard = () => {
    const [tasks, setTasks] = useState<Task[]>([]);
    const [showAddTask, setShowAddTask] = useState(false);
    const popupRef = useRef<HTMLDivElement>(null); // 👈 for detecting outside clicks

    const fetchTasks = async () => {
        try {
            const res = await axios.get("http://localhost:8080/api/v1/tasks");
            setTasks(res.data);
        } catch (error) {
            console.log(error);
        }
    };

    const handleTaskAdded = () => {
        fetchTasks();       // 👈 refresh task list
        setShowAddTask(false);  // 👈 close popup
    };


    useEffect(() => {
        fetchTasks();
    }, []);

    const handleAddTaskClick = () => setShowAddTask(true);
    const handleClosePopup = () => setShowAddTask(false);

    // ✅ Close when clicking outside
    useEffect(() => {
        if (!showAddTask) return;

        const handleClickOutside = (event: MouseEvent) => {
            if (popupRef.current && !popupRef.current.contains(event.target as Node)) {
                setShowAddTask(false);
            }
        };

        document.addEventListener("mousedown", handleClickOutside);
        return () => document.removeEventListener("mousedown", handleClickOutside);
    }, [showAddTask]);

    return (
        <div className="py-12">
            <div className="flex justify-between items-center px-2">
                <h1 className="text-xl">Tasks</h1>
                <button
                    onClick={handleAddTaskClick}
                    className="text-xs font-semibold py-0.5 border-2 border-black bg-black rounded px-4 text-white hover:text-black hover:bg-white hover:font-semibold shadow-md hover:shadow-lg transition"
                >
                    Add Task
                </button>
            </div>

            {showAddTask && (
                <div className="fixed inset-0 bg-black/20 flex items-center justify-center z-50">
                    <div
                        ref={popupRef}
                        className="relative w-[90%] sm:w-[50%] max-h-[85vh] overflow-y-auto rounded p-6 border border-gray-200 bg-white shadow-lg"
                    >
                        <button
                            onClick={handleClosePopup}
                            className="absolute top-2 right-3 text-gray-500 hover:text-black text-lg font-bold"
                        >
                            ✕
                        </button>
                        {/* ✅ Pass close handler down to AddTask */}
                        <AddTask onSuccess={handleTaskAdded} />
                    </div>
                </div>
            )}

            <div className="bg-gray-100 rounded p-2 mt-4">
                {tasks.map((task) => (
                    <div key={task.taskId} className="pb-1">
                        <TaskCard task={task} />
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Dashboard;
