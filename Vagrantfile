Vagrant.configure("2") do |config|
  # Use the laravel/homestead box
  config.vm.box = "laravel/homestead"
  config.vm.boot_timeout = 500000
  # Configuring VirtualBox provider settings
  config.vm.provider "virtualbox" do |vb|
  vb.name = "MyNewVB"
  vb.cpus = 2
  vb.memory = "2048"
  end
  # Configuring VirtualBox network settings
  config.vm.network "forwarded_port", guest: 8000, host: 8086
  # Configuring VirtualBox folder settings
  config.vm.synced_folder "test", "/website"# Provisioning with a shell script to create Laravel project
  config.vm.provision "shell", inline: <<-SHELL
  cd /
  cd website
  composer create-project laravel/laravel:8.6.* mynewproject
  cd mynewproject
  php artisan serve --host=0.0.0.0 --port=8000
  SHELL
  end  