package app.controllers;

import app.model.Avaliacao;
import app.repositories.AvaliacaoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/avaliacoes")
@CrossOrigin(origins = "*")
public class AvaliacaoController {

    private final AvaliacaoRepository repo;

    public AvaliacaoController(AvaliacaoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Avaliacao> listar(@RequestParam(required = false) Long calcadaId) {
        return (calcadaId != null) ? repo.findByCalcadaId(calcadaId) : repo.findAll();
    }

    @GetMapping("/resumo")
    public Map<String, Object> resumo(@RequestParam Long calcadaId) {
        List<Avaliacao> avals = repo.findByCalcadaId(calcadaId);

        if (avals.isEmpty()) {
            return Map.of(
                "mediaGeral", 0.0,
                "qtdAvaliacoes", 0,
                "pisoTatil", 0.0,
                "rebaixamentoGuia", 0.0,
                "iluminacaoNoturna", 0.0,
                "semObstaculos", 0.0,
                "comentarios", List.of()
            );
        }

        double media = avals.stream().mapToDouble(Avaliacao::getNotaGeral).average().orElse(0);
        int n = avals.size();

        // cálculo dos percentuais — simples, sem método auxiliar interno
        double piso   = 100.0 * avals.stream().filter(Avaliacao::isPresencaPisoTatil).count() / n;
        double reb    = 100.0 * avals.stream().filter(Avaliacao::isRebaixamentoGuia).count() / n;
        double ilum   = 100.0 * avals.stream().filter(Avaliacao::isIluminacaoNoturna).count() / n;
        double livre  = 100.0 * avals.stream().filter(Avaliacao::isSemObstaculos).count() / n;

        List<String> comentarios = avals.stream()
                .map(Avaliacao::getComentario)
                .filter(Objects::nonNull)
                .filter(s -> !s.isBlank())
                .collect(Collectors.toList());

        Map<String, Object> out = new LinkedHashMap<>();
        out.put("mediaGeral", Math.round(media * 10.0) / 10.0);
        out.put("qtdAvaliacoes", n);
        out.put("pisoTatil", Math.round(piso));
        out.put("rebaixamentoGuia", Math.round(reb));
        out.put("iluminacaoNoturna", Math.round(ilum));
        out.put("semObstaculos", Math.round(livre));
        out.put("comentarios", comentarios);
        return out;
    }
}
