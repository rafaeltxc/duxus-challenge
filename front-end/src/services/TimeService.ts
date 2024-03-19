import axios, { AxiosResponse } from "axios";
import { TimeObj } from "../types/Ambient";

export async function saveOne(time: TimeObj): Promise<TimeObj> {
  const result: AxiosResponse = await axios.post(
    "http://localhost:8080/api/v1/time/",
    time
  )
  return result.data;
}
