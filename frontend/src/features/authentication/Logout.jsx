import ButtonIcon from "../../ui/ButtonIcon";
import { HiArrowRightOnRectangle } from "react-icons/hi2";

function Logout() {
    return (
        <ButtonIcon disabled={false} onClick={() => console.log("Logout")}>
            <HiArrowRightOnRectangle />
        </ButtonIcon>
    );
}

export default Logout;
