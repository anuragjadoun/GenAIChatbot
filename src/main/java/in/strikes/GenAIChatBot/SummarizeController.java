package in.strikes.GenAIChatBot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SummarizeController {

    private final SummarizeService summarizeService;


    public SummarizeController(SummarizeService summarizeService) {

        this.summarizeService = summarizeService;

    }


    /*
     * SUMMARIZE USER QUERY
     *
     * POST /api/summarize
     */

    @PostMapping("/summarize")
    public String response(@RequestBody String query) {

        return summarizeService.summarize(query);

    }


    /*
     * ANALYZE USER SENTIMENT
     *
     * POST /api/sentiment
     */

    @PostMapping("/sentiment")
    public String sentiment(@RequestBody String query) {

        return summarizeService.analyzeSentiment(query);

    }

}