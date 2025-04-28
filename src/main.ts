import { McpServer } from "@modelcontextprotocol/sdk/server/mcp.js";
import { StdioServerTransport } from "@modelcontextprotocol/sdk/server/stdio.js";
import { ProdutoApiService } from "./infrastructure/services/ProdutoApiService.js";
import { ProdutoService } from "./application/services/ProdutoService.js";
import { ProdutoToolsController } from "./interface/controllers/ProdutoToolsController.js";

async function main() {
  const server = new McpServer({name: "produto",
    version: "1.0.0",
    capabilities: {
      resources: {},
      tools: {},
    },
  });
  const produtoApiService = new ProdutoApiService();
  const produtoService = new ProdutoService(produtoApiService);
  new ProdutoToolsController(server, produtoService);
  const transport = new StdioServerTransport();
  await server.connect(transport);
  console.error("Servidor MCP de produto rodando em stdio");
}

main().catch((error) => {
  console.error("Erro fatal:", error);
  process.exit(1);
});
