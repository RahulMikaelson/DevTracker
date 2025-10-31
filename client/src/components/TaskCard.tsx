import { type Task } from "../types/Task";

const formatEnumText = (text: string): string => {
    return text
        .toLowerCase()
        .split("_")
        .map(w => w.charAt(0).toUpperCase() + w.slice(1))
        .join(" ");
};

const formatDate = (iso: string | Date): string => {
    const date = new Date(iso);
    return date.toLocaleString("en-IN", {
        day: "2-digit",
        month: "short",
        year: "numeric",
        hour: "2-digit",
        minute: "2-digit",
    });
};


type TaskCardProps = {
    task: Task;
};

const TaskCard = ({ task }: TaskCardProps) => {
    return (
        <div className="grid grid-cols-6 py-2 px-3 bg-gray-200 hover:bg-gray-50 transition rounded">
            {/* Left side: Task details */}
            <div className="col-span-5">
                <h1 className="text-sm font-semibold text-gray-900 "><button className="hover:underline">{task.taskName}</button> </h1>
                <p className="text-xs text-gray-700">{task.taskDescription}</p>
            </div>

            {/* Right side: Status + date */}
            <div className="flex flex-col items-end justify-between">
                <span
                    className="px-2 py-0.5 rounded-full text-[10px] font-medium "
                >
                    {formatEnumText(task.taskStatus)}
                </span>
                <p className="text-[9px] text-gray-600">
                    <span className="font-medium">Created At:</span>{" "}
                    {formatDate(task.createdAt)}
                </p>
            </div>
        </div>
    );
};

export default TaskCard;
