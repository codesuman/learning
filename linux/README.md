### :computer: A Linux distribution (distro) is a complete operating system built upon the Linux kernel, which acts as the core of the OS.

Think of the Linux kernel as the engine, while a distro is the entire car, including the chassis, interior, and all the other necessary components. Distros bundle the kernel with system libraries, utilities, application software, and a package management system.

#### In essence:

* **_Linux kernel_**: The core of the operating system, responsible for managing hardware and providing essential services. 
* **_Linux distribution (distro)_**: A fully functional operating system built upon the Linux kernel, including all the software and tools needed for daily use.

**_Analogy_**: Imagine a car. The Linux kernel is the engine, and a Linux distribution is the entire car, including the chassis, interior, tires, and everything else needed for it to function.

#### Key Differences:

* Kernel: The Linux kernel is a single, unified piece of software that manages the hardware. 
* Distro: Each distro is a distinct operating system built upon the Linux kernel, with its own unique features, software, and desktop environment. Distros are like different brands or models of cars built on the same engine.

Examples of Linux Distros:
Ubuntu, Fedora, Debian, Mint, and Arch Linux.


## COMMANDS 

You can check the version of your Linux VM using any of the following commands in the terminal:

### :white_check_mark: Option 1: Check using cat on release files

```bash
cat /etc/os-release
```

This gives detailed info about your Linux distribution (works on most distros).

### :white_check_mark: Option 2: Use lsb_release
```bash
lsb_release -a
```

This gives info like Distributor ID, Description, Release, and Codename (works if lsb-release package is installed).

### :white_check_mark: Option 3: Check kernel version
```bash
uname -r
```

This shows the Linux kernel version, e.g., 5.15.0-91-generic.

### :white_check_mark: Option 4: Use hostnamectl (for systemd-based distros)
```bash
hostnamectl
```

It shows OS name, kernel, architecture, and more.