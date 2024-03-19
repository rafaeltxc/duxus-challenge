import axios, { AxiosResponse } from "axios";
import { IntegranteObj } from "../types/Ambient";

export async function findAll(): Promise<IntegranteObj[]> {
  const result: AxiosResponse = await axios.get("http://localhost:8080/api/v1/integrante/")
  return result.data;
}

export async function saveOne(integrante: IntegranteObj): Promise<IntegranteObj> {
  const result: AxiosResponse = await axios.post(
    "http://localhost:8080/api/v1/integrante/",
    integrante
  );
  return result.data;
}
