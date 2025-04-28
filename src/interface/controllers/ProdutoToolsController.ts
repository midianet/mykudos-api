import { McpServer } from "@modelcontextprotocol/sdk/server/mcp.js";
import { z } from "zod";
import { ProdutoService as ProdutoService } from "../../application/services/ProdutoService.js";

export class ProdutoToolsController {
  constructor(
    private server: McpServer,
    private produtoService: ProdutoService
  ) {this.registerTools()}

  private registerTools(): void {
    this.registerListarProdutosTodos();
    this.registerListarProdutosPorDescricao();
    this.registerListarProdutosPorGrupo();
    this.registerListarProdutosPorClasse();
    this.registerListarProdutosPorValor();
    this.registerListarProdutosPorPeso();
  }


  private registerListarProdutosTodos(): void {
    this.server.tool(
      "listar-produtos",
      "Lista todos os produtos",
      async () => {
        const produtos = await this.produtoService.getProdutosTodos();
        return {
          content: [
            {
              type: "text",
              text: produtos,
            },
          ],
        };
      }
    );
  }

  private registerListarProdutosPorDescricao(): void {
    this.server.tool(
      "listar-produtos-descricao",
      "Lista produtos por descrição",
      {descricao: z.string()},
      async ({ descricao: state }) => {
        const produtos = await this.produtoService.getProdutosPorDescricao(state);
        return {
          content: [
            {
              type: "text",
              text: produtos,
            },
          ],
        };
      }
    );
  }

  private registerListarProdutosPorGrupo(): void {
    this.server.tool(
      "listar-produtos-grupo",
      "Lista produtos por grupo",
      {grupo: z.string()},
      async ({ grupo: state }) => {
        const produtos = await this.produtoService.getProdutosPorGrupo(state);
        return {
          content: [
            {
              type: "text",
              text: produtos,
            },
          ],
        };
      }
    );
  }

  private registerListarProdutosPorClasse(): void {
    this.server.tool(
      "listar-produtos-classe",
      "Lista produtos por classe",
      {classe: z.string()},
      async ({ classe: state }) => {
        const produtos = await this.produtoService.getProdutosPorClasse(state);
        return {
          content: [
            {
              type: "text",
              text: produtos,
            },
          ],
        };
      }
    );
  }

  private registerListarProdutosPorValor(): void {
    this.server.tool(
      "listar-produtos-valor",
      "Lista produtos por valor",
      {valor: z.number()},
      async ({ valor: state }) => {
        const produtos = await this.produtoService.getProdutosPorValor(state);
        return {
          content: [
            {
              type: "text",
              text: produtos,
            },
          ],
        };
      }
    );
  }


  private registerListarProdutosPorPeso(): void {
    this.server.tool(
      "listar-produtos-peso",
      "Lista produtos por peso",
      {peso: z.number()},
      async ({ peso: state }) => {
        const produtos = await this.produtoService.getProdutosPorValor(state);
        return {
          content: [
            {
              type: "text",
              text: produtos,
            },
          ],
        };
      }
    );
  }


}
