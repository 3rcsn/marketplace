package org.ercsn.marketplace.catalog.infrastructure.http;

import org.ercsn.marketplace.catalog.application.BrowseShowcaseUseCase;
import org.ercsn.marketplace.catalog.application.dto.EventOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ercsn.marketplace.catalog.domain.Event;

import java.util.List;

@RestController
@RequestMapping("/showcase")
public class ShowCaseController {
    private  final BrowseShowcaseUseCase browseShowcaseUseCase;

    public ShowCaseController(BrowseShowcaseUseCase browseShowcaseUseCase) {
        this.browseShowcaseUseCase = browseShowcaseUseCase;
    }

    @GetMapping
    List<EventOutput> browseShowcase() {
        return browseShowcaseUseCase.execute();
    }
}
