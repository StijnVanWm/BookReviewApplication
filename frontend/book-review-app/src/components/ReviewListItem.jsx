import PropTypes from 'prop-types'

const ReviewListItem = (props) => {

    const { review: {id, creationDateTime, description, user: { username } }} = props;

    return (
        <li className={`bg-white w-full h-1/3 flex flex-col gap-y-2 p-2 group hover:cursor-pointer even:bg-gray-100`}>
            <div className="flex justify-between">
                <span className="text-xs text-sky-600 group-hover:opacity-70">{username}</span>
                <span className="text-xs text-sky-600 group-hover:opacity-70">{new Date(creationDateTime).toLocaleDateString('nl-BE', {month: '2-digit', day: '2-digit', year:'numeric'})}</span>
            </div>
            <p className="text-sm text-sky-950 text-ellipsis overflow-hidden group-hover:opacity-70">
                {description}
            </p>
        </li>
    )
}

ReviewListItem.propTypes = {
    review: PropTypes.object
}

export default ReviewListItem