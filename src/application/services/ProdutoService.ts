import { Produto} from "../../domain/models/Produto.js";
import { ProdutoApiService } from "../../infrastructure/services/ProdutoApiService.js";

export class ProdutoService {
  constructor(private apiService: ProdutoApiService) {}

  formatProduto(produto: Produto): string {
    return [
      `Descrição: ${produto.descricao || "Desconhecido"}`,
      `Grupo: ${produto.grupo || "Desconhecido"}`,
      `Classe: ${produto.classe || "Desconhecido"}`,
      `Valor: ${produto.valor || "Desconhecido"}`,
      `Peso: ${produto.peso || "Desconhecido"}`,
      "---",
    ].join("\n");
  }

  async getProdutosTodos(): Promise<string> {
    const data = await this.apiService.getProdutosTodos()
    if (!data) return "Falha ao obter dados"
    const produtos = data.content || []
    if (produtos.length === 0) return `Não tem nenhum produto cadastrado`
    const produtosFormatados = produtos.map((produto) => this.formatProduto(produto))
    return `Todos os Produtos:\n\n${produtosFormatados.join("\n")}`
  }

  async getProdutosPorDescricao(descricao: string): Promise<string> {
    const data = await this.apiService.getProdutosPorDescricao(descricao)
    if (!data) return "Falha ao obter dados"
    const produtos = data.content || []
    if (produtos.length === 0) return `Não tem produtos cadastrados com a descrição: ${descricao}`
    const produtosFormatados = produtos.map((produto) => this.formatProduto(produto))
    return `Produtos com a descrição: ${descricao}:\n\n${produtosFormatados.join("\n")}`
  }

  async getProdutosPorGrupo(grupo: string): Promise<string> {
    const data = await this.apiService.getProdutosPorGrupo(grupo.toUpperCase())
    if (!data) return "Falha ao obter dados"
    const produtos = data.content || []
    if (produtos.length === 0) return `Não tem produtos cadastrados com esse grupo: ${grupo}`
    const produtosFormatados = produtos.map((produto) => this.formatProduto(produto))
    return `Produtos com o grupo: ${grupo}:\n\n${produtosFormatados.join("\n")}`
  }

  async getProdutosPorClasse(classe: string): Promise<string> {
    const data = await this.apiService.getProdutosPorClasse(classe.toUpperCase())
    if (!data) return "Falha ao obter dados"
    const produtos = data.content || []
    if (produtos.length === 0) return `Não tem produtos cadastrados com essa classe: ${classe}`
    const produtosFormatados = produtos.map((produto) => this.formatProduto(produto))
    return `Produtos com a classe: ${classe}:\n\n${produtosFormatados.join("\n")}`
  }

  async getProdutosPorValor(valor: number): Promise<string> {
    const data = await this.apiService.getProdutosPorValor(valor)
    if (!data) return "Falha ao obter dados"
    const produtos = data.content || []
    if (produtos.length === 0) return `Não tem produtos cadastrados com esse valor: ${valor}`
    const produtosFormatados = produtos.map((produto) => this.formatProduto(produto))
    return `Produtos com o valor: ${valor}:\n\n${produtosFormatados.join("\n")}`
  }

  async getProdutosPorPeso(peso: number): Promise<string> {
    const data = await this.apiService.getProdutosPorPeso(peso)
    if (!data) return "Falha ao obter dados"
    const produtos = data.content || []
    if (produtos.length === 0) return `Não tem produtos cadastrados com esse valor: ${peso}`
    const produtosFormatados = produtos.map((produto) => this.formatProduto(produto))
    return `Produtos com o valor: ${peso}:\n\n${produtosFormatados.join("\n")}`
  }

}