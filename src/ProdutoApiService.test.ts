import { describe, it, expect, beforeEach } from 'vitest';
import { ProdutoApiService } from './infrastructure/services/ProdutoApiService.js';

describe('ProdutoApiService (integração)', () => {
  let produtoApiService: ProdutoApiService;

  beforeEach(() => {
    produtoApiService = new ProdutoApiService();
  });

  it('deve retornar um ProdutoResponse válido', async () => {
    const result = await produtoApiService.getProdutosPorDescricao('Play');
    console.log(result);
    expect(result).not.toBeNull();
  });

});
