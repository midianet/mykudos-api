import { describe, it, expect, beforeEach } from 'vitest';
import { ProdutoApiService } from './infrastructure/services/ProdutoApiService.js';
import { ProdutoService } from './application/services/ProdutoService.js';

describe('ProdutoApiService (integração)', () => {
  let produtoService: ProdutoService;

  beforeEach(() => {
    const produtoApiService = new ProdutoApiService();
    produtoService = new ProdutoService(produtoApiService);
  });

  it('deve chamar a api de Produto', async () => {
    const result = await produtoService.getProdutosPorDescricao('Play');
    console.log(result);
    expect(result).not.toBeNull();
  });

});
