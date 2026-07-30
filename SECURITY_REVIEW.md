# Security and robustness review

Review date: 2026-07-30

## Changes made

- Removed model output from standard output because it can contain proprietary
  source code or other sensitive input.
- Added null, blank, and size validation before sending content to the API.
- Added a clear delimiter between application instructions and source-code input.
- Replaced unbounded, platform-default file reading with bounded UTF-8 reads.
- Preserved source formatting instead of trimming every line.
- Replaced swallowed file errors and stack traces with user-visible errors and
  structured logging.
- Restored thread interrupt status and added bounded exponential retry backoff.
- Made embedded prompts immutable.
- Expanded ignore rules for secrets, editor metadata, and operating-system files.
- Documented API-key handling and the source-file safety limit.

## Remaining recommendations

- Add automated unit tests and a CI workflow; the repository currently contains
  no tests.
- Add a privacy notice explaining that selected source code is transmitted to
  the configured AI provider.
- Configure a real HTTP call timeout in the SDK client. The current UI timeout is
  checked between attempts and cannot stop a call that is already blocked.
- Retry only transient network and rate-limit failures. Avoid retrying
  authentication, validation, and other permanent failures.
- Enable GitHub dependency updates, secret scanning, and code scanning.
- Remove already-tracked `bin/` and `.DS_Store` files from Git history in the
  repository. They are now ignored, but ignore rules do not untrack old files.
