To create a named stash in Git, use the git stash push command with the -m or --message option followed by the desired name in quotes. This allows you to easily identify and retrieve specific stashes later. 

### Here's how to do it:

### Stashing with a name:
```bash
   git stash push -m "feature/new-login-ui"
```


### List stashes:
To see all your stashes, including the names you've given them, use:
```bash
   git stash list
```

This will output a list like this: 

```bash
    stash@{0}: On main: feature/new-login-ui
    stash@{1}: On main: fix/bug-123
```

### Finding a Stash by Name (Advanced): 
If you have many stashes, you can use git stash list | grep "your stash name" to filter the list and find the specific stash you're looking for. 

```bash
    git stash list | grep "feature/new-login-ui"
```

* This will output the matching stash entry.

### Apply a named stash:
To apply a specific stash (e.g., the one named "My descriptive stash name"), use:
```bash
    git stash apply stash^{/feature/new-login-ui}
```

This will apply the changes from the stash to your working directory. 

### Apply & remove a Named Stash:
To apply a stash and remove it from the stash list, use git stash pop with the same reference format: 
```bash
    git stash pop stash^{/feature/new-login-ui}
```

### Dropping a Stash by Name:
To remove a stash from the list : 

```code
     git stash drop stash@{0} # Example, replace with the actual stash reference
```

#### Best Practices:

* Use descriptive names that clearly explain the purpose of the stash.
* Include issue or ticket numbers, branch names, or timestamps when relevant.
* Regularly review and clean up your stashes to avoid clutter.