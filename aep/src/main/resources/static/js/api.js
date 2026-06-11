// Funções de comunicação com a API do ObservaAção
// O frontend é servido pelo próprio Spring Boot, então usamos caminhos relativos

// Trata a resposta da API: retorna o JSON em caso de sucesso ou lança
// um erro com a mensagem do servidor (validação 400, não encontrado 404, etc.)
async function tratarResposta(resposta) {
    if (resposta.ok) {
        return resposta.json();
    }

    let mensagem = 'Não foi possível concluir a operação. Tente novamente.';
    try {
        const corpo = await resposta.json();
        if (corpo.erros && corpo.erros.length) {
            // Erros de validação: junta todas as mensagens de campo
            mensagem = corpo.erros.map(function (e) { return e.mensagem; }).join('\n');
        } else if (corpo.erro) {
            mensagem = corpo.erro;
        }
    } catch (e) {
        // Corpo vazio ou não-JSON: mantém a mensagem padrão
    }
    throw new Error(mensagem);
}

// Retorna todas as categorias cadastradas
async function getCategorias() {
    const resposta = await fetch('/categorias');
    if (!resposta.ok) throw new Error('Erro ao carregar categorias');
    return resposta.json();
}

// Cria um novo usuário (quando o cidadão se identifica)
async function criarUsuario(usuario) {
    const resposta = await fetch('/usuarios', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(usuario)
    });
    return resposta.json();
}

// Cria um novo chamado
async function criarChamado(chamado) {
    const resposta = await fetch('/chamados', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(chamado)
    });
    return tratarResposta(resposta);
}

// Busca um chamado pelo número de protocolo
async function buscarPorProtocolo(protocolo) {
    const resposta = await fetch('/chamados/protocolo/' + protocolo);
    if (!resposta.ok) return null;
    return resposta.json();
}

// Lista todos os chamados (painel do servidor)
async function listarChamados() {
    const resposta = await fetch('/chamados');
    return resposta.json();
}

// Atualiza o status de um chamado (envia o enum como string JSON)
async function atualizarStatus(chamadoId, novoStatus) {
    const resposta = await fetch('/chamados/' + chamadoId, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(novoStatus)
    });
    return tratarResposta(resposta);
}

// Adiciona uma movimentação no histórico do chamado
async function adicionarHistorico(chamadoId, historico) {
    const resposta = await fetch('/historicos/' + chamadoId, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(historico)
    });
    return tratarResposta(resposta);
}

// Busca o histórico de um chamado específico
async function getHistoricoPorChamado(chamadoId) {
    const resposta = await fetch('/historicos/chamado/' + chamadoId);
    return resposta.json();
}

// Formata o número de protocolo com o ano: 2026-000001
function formatarProtocolo(protocolo) {
    const ano = new Date().getFullYear();
    return ano + String(protocolo).padStart(3, '0');
}

// Converte o enum de status para texto legível
function formatarStatus(status) {
    if (status === 'PENDENTE')     return 'Aberto';
    if (status === 'EM_ANALISE')   return 'Em Análise';
    if (status === 'EM_EXECUCAO')  return 'Em Execução';
    if (status === 'FINALIZADO')   return 'Finalizado';
    if (status === 'CANCELADO')    return 'Cancelado';
    return status;
}

// Classe CSS do badge de status
function classeBadgeStatus(status) {
    if (status === 'PENDENTE')    return 's-open';
    if (status === 'EM_ANALISE')  return 's-review';
    if (status === 'EM_EXECUCAO') return 's-exec';
    if (status === 'FINALIZADO')  return 's-done';
    if (status === 'CANCELADO')   return 's-cancel';
    return 's-open';
}

// Retorna HTML completo do badge de status
function badgeStatusHTML(status) {
    return '<span class="badge-oa ' + classeBadgeStatus(status) + '">' +
           '<span class="bd"></span>' + formatarStatus(status) + '</span>';
}

// Converte o enum de prioridade para texto legível
function formatarPrioridade(prioridade) {
    if (prioridade === 'BAIXA') return 'Baixa';
    if (prioridade === 'MEDIA') return 'Média';
    if (prioridade === 'ALTA')  return 'Alta';
    return prioridade;
}

// Classe CSS do badge de prioridade
function classeBadgePrioridade(prioridade) {
    if (prioridade === 'BAIXA') return 'p-low';
    if (prioridade === 'MEDIA') return 'p-mid';
    if (prioridade === 'ALTA')  return 'p-high';
    return 'p-low';
}

// Retorna HTML completo do badge de prioridade
function badgePrioHTML(prioridade) {
    return '<span class="badge-prio ' + classeBadgePrioridade(prioridade) + '">' +
           formatarPrioridade(prioridade) + '</span>';
}

// Formata a data no padrão brasileiro
function formatarData(dataString) {
    if (!dataString) return '—';
    const data = new Date(dataString);
    return data.toLocaleString('pt-BR');
}
