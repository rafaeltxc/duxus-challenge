import axios, { AxiosResponse } from "axios";
import { ComposicaoObj } from "../types/Ambient";

export async function saveMany(composicoes: ComposicaoObj[]): Promise<ComposicaoObj[]> {
  const result: AxiosResponse = await axios.post(
    "http://localhost:8080/api/v1/composicao/save-many",
    composicoes
  )
  return result.data;
}
