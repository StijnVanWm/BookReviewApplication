
const ReviewListItem = (props) => {

    const { review: {id, description, user: { username } }} = props;

    return (
        <li className={`${id%2!==0 ? 'bg-gray-100' : 'bg-white'} w-full h-1/3 flex flex-col gap-y-2 p-2 group hover:cursor-pointer`}>
            <span className="text-xs text-sky-600 block h-fit group-hover:opacity-70">{username}</span>
            <p className="text-sm text-sky-950 text-ellipsis overflow-hidden group-hover:opacity-70">
                {description}
            </p>
        </li>
    )
}

export default ReviewListItem