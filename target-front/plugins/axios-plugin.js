import {setClient} from "../utils/apiClient";

export default ({ app }) => {
  setClient(app.$axios)
}
