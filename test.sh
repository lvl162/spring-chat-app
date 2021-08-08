git filter-branch --env-filter '
    if [ "$GIT_AUTHOR_NAME" = "Ganesh Dhareshwar" ]; then \
        export GIT_AUTHOR_NAME="Luu Van Loi" GIT_AUTHOR_EMAIL="loithui162@gmai.com"; \
    fi
    '