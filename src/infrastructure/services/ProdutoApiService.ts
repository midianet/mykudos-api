import {
  ProdutoResponse,
} from "../../domain/models/Produto.js";

export class ProdutoApiService {
  private readonly API_BASE = "http://localhost:8080"
  private readonly USER_AGENT = "produto-app/1.0"

  // Helper function for making NWS API requests
  async makeRequest<T>(endpoint: string): Promise<T | null> {
    const url = `${this.API_BASE}${endpoint}`
    const headers = {
      "User-Agent": this.USER_AGENT,
      Accept: "application/json",
    }
    try {
      const response = await fetch(url, { headers })
      if (!response.ok) throw new Error(`HTTP erro! status: ${response.status}`)
      return (await response.json()) as T
    } catch (error) {
      console.error("Erro na api Produto:", error)
      return null;
    }
  }

  async getProdutosPorDescricao(descricao: string): Promise<ProdutoResponse | null> {
    return this.makeRequest<ProdutoResponse>(`/produtos?page=0&size=2000&descricao=${descricao}`);
  }

  async getProdutosPorGrupo(grupo: string): Promise<ProdutoResponse | null> {
    return this.makeRequest<ProdutoResponse>(`/produtos?page=0&size=2000&grupo=${grupo}`);
  }

  async getProdutosPorClasse(classe: string): Promise<ProdutoResponse | null> {
    return this.makeRequest<ProdutoResponse>(`/produtos?page=0&size=2000&classe=${classe}`);
  }

  async getProdutosPorValor(valor: number): Promise<ProdutoResponse | null> {
    return this.makeRequest<ProdutoResponse>(`/produtos?page=0&size=2000&valor=${valor}`);
  }

  async getProdutosPorPeso(peso: number): Promise<ProdutoResponse | null> {
    return this.makeRequest<ProdutoResponse>(`/produtos?page=0&size=2000&peso=${peso}`);
  }

  async getProdutosTodos(): Promise<ProdutoResponse | null> {
    return this.makeRequest<ProdutoResponse>(`/produtos?page=0&size=2000`);
  }
  
}
