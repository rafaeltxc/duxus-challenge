export interface IntegranteObj {
  integranteId?: number;
  nome: string;
  funcao: string;
  franquia: string;
}

export interface TimeObj {
  timeId: number;
  nome: string;
  data: Date;
}

export interface ComposicaoObj {
  composicaoId?: number;
  integrante: {
    integranteId: number;
  };
  time: {
    timeId: number;
  }
}

export interface RecordType {
  key: string;
  title: string;
}
