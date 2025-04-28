export interface Produto {
    descricao?: string;
    grupo?: string;
    classe?: string;
    valor?: number;
    peso?: number;
}

export interface ProdutoResponse {
  content: Produto[];
}