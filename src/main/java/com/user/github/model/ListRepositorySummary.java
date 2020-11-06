package com.user.github.model;

import java.util.List;
import lombok.*;

@Getter
@RequiredArgsConstructor
@ToString
public class ListRepositorySummary {

	private List<RepositorySummary> RepositorySummaries; 
}
